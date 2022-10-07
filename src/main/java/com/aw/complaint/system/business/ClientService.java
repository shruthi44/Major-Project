package com.aw.complaint.system.business;


import com.aw.complaint.system.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@SessionScope
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    private Client client;
    public ClientService() {
    }
    public ClientService(Client client) {
        this.client = client;
    }
    public Client getClient() {
        return client;
    }
    public void signUp(Client clientInfo) {
        client = clientRepository.save(clientInfo);

    }


    public boolean logIn(String emailID) {
        List<Client> clientList = clientRepository.findByEmailId(emailID);
        if (clientList.size() > 0) {
            client = clientList.get(0);
            return true;
        }
        return false;
    }

    public void createComplaint(Complaint complaint) {
        complaint.setRegisteredDateTime(LocalDateTime.now());
        client.addComplaint(complaint);
        clientRepository.save(client);
    }
    public void logOut(HttpServletRequest request){
        request.getSession().invalidate();
    }

}
