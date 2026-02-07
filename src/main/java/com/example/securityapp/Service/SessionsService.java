package com.example.securityapp.Service;


import com.example.securityapp.Entities.Sessions;
import com.example.securityapp.Entities.UserEntity;
import com.example.securityapp.Repositories.SessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionsService {

    private final SessionsRepository sessionsRepository;

    public void storingsession(UserEntity user, String refreshtoken) {
        List<Sessions> sessions= sessionsRepository.findByUser(user);

        if(sessions.size()==2){
            sessions.sort(Comparator.comparing(Sessions::getSession_createdat));

            sessionsRepository.delete(sessions.get(0));

        }

        Sessions newsession= Sessions.builder()
                .user(user)
                .refreshtoken(refreshtoken)
                .build();
        sessionsRepository.save(newsession);

    }


    //Need to validate the refresh token generated with the session present if yes generate the new accesstoken

    public void validaterefreshtoken(String refreshtoken){

        Sessions session=sessionsRepository.findByrefreshtoken(refreshtoken);

        session.setSession_createdat(LocalDateTime.now());
        sessionsRepository.save(session);





    }





}
