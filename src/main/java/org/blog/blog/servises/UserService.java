package org.blog.blog.servises;

import jakarta.servlet.http.HttpSession;
import org.blog.blog.dto.UserDTO;
import org.blog.blog.entities.Post;
import org.blog.blog.entities.User;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDTO registration(UserDTO userDTO);

    User getUser();

    UserDTO login(UserDTO userDTO);

    void saveAvatar(MultipartFile avatar);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);
}

