package hello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Oauth_access_token {

    private java.sql.Blob token;
    private String token_id;

    @Id
    private String authentication_id;
    private String user_name;
    private String client_id;
    private java.sql.Blob authentication;
    private String refresh_token;





}
