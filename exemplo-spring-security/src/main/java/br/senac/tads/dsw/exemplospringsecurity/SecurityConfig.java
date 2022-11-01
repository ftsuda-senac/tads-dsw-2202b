package br.senac.tads.dsw.exemplospringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static PasswordEncoder plainPasswordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence cs) {
                return cs.toString();
            }

            @Override
            public boolean matches(CharSequence cs, String hashSenha) {
                return hashSenha
                        != null && hashSenha.equals(cs.toString());
            }
        };
    }

    public static PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return plainPasswordEncoder();
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
       // @formatter:off
        http.headers()
                .cacheControl().disable() // Desabilita controle de cache
                .frameOptions().sameOrigin() // evita problema ao abrir console do H2
            .and().csrf().disable()
                .authorizeRequests()
                    .antMatchers("/favicon/**", "/webjars/**", "/css/**", "/js/**",
                            "/font/**", "/", "/index.html", "/h2-console/**").permitAll()
                    .antMatchers("/sessao/**").permitAll()
//                    .antMatchers("/protegido/peao").hasAuthority("ROLE_PEAO") // OU hasRole("PEAO")
//                    .antMatchers("/protegido/fodon").hasAuthority("ROLE_FODON")
//                    .antMatchers("/protegido/god").hasAuthority("ROLE_GOD")
                    .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login") // URL de acesso configurado no LoginController
                    .usernameParameter("username")
                    .passwordParameter("senha")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/erro/login")
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
            .and()
                .exceptionHandling()
                    .accessDeniedPage("/erro/403");
                    
        // @formatter:on
    }

}
