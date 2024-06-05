package com.hana.app.service.user;

import com.hana.app.repository.PbRepository;
import com.hana.app.repository.UsersRepository;
import com.hana.dto.request.PbPwdCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PbService {

    final PbRepository pbRepository;
    final UsersRepository usersRepository;

    public boolean isPbAuthenticated(PbPwdCheckDto pbPwdCheckDto) {
//        String password = requestData.get("pwd");
//        String writtenPwd = requestData.get("writtenPwd");
        Long userId = pbRepository.findUserIdById(pbPwdCheckDto.getPbId());
        String password = usersRepository.findPasswordById(userId);
//        System.out.println(password);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(pbPwdCheckDto.getInputPwd(), password)) {
            return true;
        }else{
            return false;
        }
    }
}
