package sales.savvy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sales.savvy.dto.LoginData;
import sales.savvy.entity.User;
import sales.savvy.repository.UserRepository;

@Service
public class UserServiceImplementation 
implements UserService {
	@Autowired
	UserRepository repo;

	@Override
	public void addUser(User user) {
		repo.save(user);
		// TODO Auto-generated method stub
		
	}
	@Override
	public User getUser(String username) {
		return repo.findByUsername(username);

}
	@Override 
	public String validateUser(LoginData data) {
		String webUsername = data.getUsername();
		String webPassword = data.getPassword();
	//username is does not exist i db
		User u = getUser(webUsername);
		if(u == null) {
			return "invalid";
			
		}
		else {
			String dbPassword = u.getPassword();
			//valid credentials
			if(webPassword.equals(dbPassword)) {
				String role = u.getRole();
				if(role.equals("admin"))
					return "admin";
				else 
					return "customer";
			}
			else {
				return "invalid";
			}
				
		}
	}
}
