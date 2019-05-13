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
public class Oauth_client_details {

            @Id
            public String client_id;
        private String client_secret;
        private String web_server_redirect_uri;
        private String scope;
        private int access_token_validity;
        private int refresh_token_validity;
        private String resource_ids;
        private String authorized_grant_types;
        private String authorities;
        private String additional_information;
        private String autoapprove;
}
