package com.springboot.financialplanning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.financialplanning.security.JwtAuthenticationEntryPoint;
import com.springboot.financialplanning.security.JwtAuthenticationFilter;



@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	public SecurityConfig(UserDetailsService userDetailsService,
							JwtAuthenticationEntryPoint authenticationEntryPoint,
							JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
			.authorizeHttpRequests((authorize) -> 
			//authorize.anyRequest().authenticated()
			authorize.antMatchers( "/api/**").permitAll().
			antMatchers("/api/product/delete/**").permitAll()
			.antMatchers("/api/authenticate/**").permitAll()
			.antMatchers("/signup**").permitAll()
			.antMatchers("/login**").permitAll()
			.antMatchers("/api/product/**").permitAll()
			.antMatchers("/api/v1/productapp/addProduct**").permitAll()
			
			.antMatchers("/user/login","/executive/getone/{uid").permitAll()
			
		    .antMatchers("/executive/add","/thematicfund/add/{cid}","/insurance/add/{cid}","/investormutualfund/add/{iid}/{mfid}","/investorinsurance/add/{iid}/{inid}").permitAll()
		    .antMatchers("/insurance/all/{cid}","insurance/getall","insurance/getone/{inid}","insurance/delete/{inid}","insurance/delete/{inid}/{cid}","insurance/update/{inid}","insurance/update/{inid}/{cid}").permitAll()
			.antMatchers("/company/add","company/getone/{cid}","/company/update/{cid}").hasAnyAuthority("SALES_VP")
			.antMatchers("/executive/getone/{uid}").permitAll()
			.antMatchers("/investorthematicfund/add/{iid}/{tfid}","/tdall","/thematicdetails/{iid}/{tid}","/deletethematic/{tdid}","/search","/updatethematic/{tdid}","/category/{category}").permitAll()
			.antMatchers("/investor/add","/investor/getone/{iid}","investor/update/{iid}","/investorwithdrawlist/{investorId}").permitAll()
			.antMatchers("/hr/add","/hr/getone/{hid}").permitAll()
			.antMatchers("/salesvp/add","/salesvp/getone/{sid}").permitAll()
			.antMatchers("/mutualfund/add/{cid}","/investormutualfunddetails/{iid}/{mfid}","/by-company","/by-category","/searchByCategory","/mdall","/delete/{mdid}","/category/{category}","/updatemutualfund/{mdid}","/filter").permitAll()
			.antMatchers("/searchByCompanyName","/searchByFundType").permitAll()
			.antMatchers("/lumpsum/calculate").permitAll()
			.antMatchers("/sip/calculate","/generate-otp","/verify-otp","/withdraw/{iind}","/mutualfundwithdraw/all","/InvestorThematicFundwithdraw/{tid}","/thematicfundwithdraw/all","/withdrawall/{cid}","/investorwithdrawlist/{investorId}/{dateOfInvestment}").permitAll()
			.anyRequest().authenticated())
			.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
 
}
