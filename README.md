# OAuth2 with Spring Security
The goal of this project is to provide a ready to use Spring Security Web App skeleton that supports:
- multiple clients (Browser, Web Apps, Mobile Apps)
- to have a federated identity, e.g. users stored in a local "DB/Memory" and external OAuth provider, such as Facebook 
- OAuth2 to be used a standard protocol for communication 

## Run HTTP Server
	
	mvn ....


## How to Consume Spring OAuth2
[TBD]

	curl -k -i -H "Accept: application/json" "localhost:8080/oauth/authorize?client_id=client&client_secret=secret&response_type=code"
	curl -k -i -H "Accept: application/json" "localhost:8080/oauth/token?client_id=client&client_secret=secret&response_type=token&grant_typization_code&code="


## Ref
### Spring OAuth2 Docs
http://projects.spring.io/spring-security-oauth/docs/oauth2.html

### Spring Security OAuth2 Examples
There are very good examples as part of the [Spring Security OAuth Source](https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2)
, which is using a lot of XML that makes it hard to read.

### Josh's REST Stack
Josh's awesome [OAuth Project](https://github.com/joshlong/the-spring-rest-stack/tree/master/code/web/oauth) repo has most of it as well.
