package Twitter.Entities.Record;

import Twitter.Entities.User.Model.User;
import Twitter.Entities.User.Model.UserDto;
import Twitter.Entities.User.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/record")
public class RecordMvcController {
    private final RecordService recordService;
    private final UserService us;

    public RecordMvcController(RecordService recordService, UserService us) {
        this.recordService = recordService;
        this.us = us;
    }

    @GetMapping
    public String getRecords(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int size,
                           Model model) {
        final Page<RecordDto> records = recordService.findAllRecords(page, size)
                .map(RecordDto::new);
        model.addAttribute("records", records);
        final int totalPages = records.getTotalPages();
        final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .toList();
        model.addAttribute("pages", pageNumbers);
        model.addAttribute("totalPages", totalPages);
        return "record";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editRecord(@PathVariable(required = false) Long id, Model model){
        if (id == null || id <= 0)
            model.addAttribute("recordDto", new RecordDto());
        else{
            model.addAttribute("recordId", id);
            model.addAttribute("recordDto", new RecordDto(recordService.findRecord(id)));
        }
        return "record-edit";
    }
    @PostMapping(value = {"", "/{id}/"})
    public String saveCar(@PathVariable(required = false) Long id,
                          @ModelAttribute @Valid RecordDto recordDto,
                          BindingResult bindingResult,
                          Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "record-edit";
        }
        if (id == null || id <= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            recordService.AddRecord(recordDto.getText(), us.findByLogin(auth.getName())); //recordDto.getUser());
        }
        //else
        //    recordService.updateCar(id, recordDto.getManufacturer(), recordDto.getModel());
        return "redirect:/record";
    }
}
