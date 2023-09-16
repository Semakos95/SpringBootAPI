package gr.cinema.api.validation;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
public class EmailValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailValidationService.class);
    private UserRepository userRepository;
    @Autowired
    public EmailValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static boolean patternMatches(String emailAddress, String regexPattern){
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }


    public boolean testUsingSimpleRegex(UserDTO userDTO){
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            LOGGER.error("validateEmailUsingSimpleRegex(): there is a body 'email': {}", userDTO.getEmail());
            // throw new BadRequestException();
            return false;
        }

        String regexPattern = "^(.+)@(\\S+)$";
        return patternMatches(userDTO.getEmail(), regexPattern);
    }


    public boolean testUsingStrictRegex(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            LOGGER.error("validateEmailUsingStrictRegex(): there is a body 'email': {}", userDTO.getEmail());
            // throw new BadRequestException();
            return false;
        }

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return patternMatches(userDTO.getEmail(), regexPattern);
    }
}
