package Twitter.Entities.Record;

import Twitter.Entities.Exceptions.RecordNotFoundException;
import Twitter.Entities.User.Model.User;
import Twitter.Util.Validation.ValidatorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final ValidatorUtil validatorUtil;

    public RecordService(RecordRepository recordRepository, ValidatorUtil validatorUtil){
        this.recordRepository = recordRepository;
        this.validatorUtil = validatorUtil;
    }
    public Page<Record> findAllRecords(int page, int size) {
        return recordRepository.findAll(PageRequest.of(page - 1, size, Sort.by("date").descending()));
    }

    public Record AddRecord(String text, User user){
        final Record record = new Record(text, new Date(), user);
        validatorUtil.validate(record);
        return recordRepository.save(record);
    }
    public Record findRecord(Long id){
        final Optional<Record> record = recordRepository.findById(id);
        return record.orElseThrow(() -> new RecordNotFoundException(id));
    }
}
