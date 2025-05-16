package it.uniroma3.siw.siwbooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Assicurati che l'enum Ruolo sia importato correttamente
import static it.uniroma3.siw.siwbooks.model.Ruolo.ADMIN;
import static it.uniroma3.siw.siwbooks.model.Ruolo.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    // Risorse statiche e pagine di base accessibili a tutti
                    .requestMatchers(HttpMethod.GET, "/", "/index", "/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/login", "/register").permitAll() // Pagine di login e registrazione
                    .requestMatchers(HttpMethod.POST, "/register").permitAll() // Azione di registrazione

                    // Utente occasionale: visualizzazione di libri, autori e dettagli (recensioni incluse nei dettagli libro)
                    .requestMatchers(HttpMethod.GET, "/libri", "/libri/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/autori", "/autori/**").permitAll()

                    // Utente registrato (USER): può aggiungere recensioni
                    .requestMatchers(HttpMethod.GET, "/libri/*/recensioni/new").hasAnyAuthority(USER.name(), ADMIN.name())
                    .requestMatchers(HttpMethod.POST, "/libri/*/recensioni").hasAnyAuthority(USER.name(), ADMIN.name())

                    // Amministratore (ADMIN): accesso completo alle funzionalità di amministrazione
                    .requestMatchers("/admin/**").hasAuthority(ADMIN.name()) 

                    // Qualsiasi altra richiesta necessita di autenticazione
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/libri", true) // Reindirizza alla lista libri dopo il login
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Specifica l'URL per il logout
                    .logoutSuccessUrl("/login?logout") // Reindirizza qui dopo il logout
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
            );
        return http.build();
    }
}