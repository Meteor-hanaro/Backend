package com.hana.app.service.user;

import com.hana.app.data.entity.Pb;
import com.hana.app.repository.user.PbRepository;
import com.hana.app.repository.user.UsersRepository;
import com.hana.dto.request.PbPwdCheckDto;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;

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

    public Pb findByPbId(Long pbId) {
        Pb pb = pbRepository.findById(pbId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND));
        return pb;
    }
}
