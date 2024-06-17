# Backend

😎 Gold Rounge의 Backend Repository

## 🚀 개발 환경
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17.0.11-515151?style=flat-square"> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/><img src="https://img.shields.io/badge/3.2.5-515151?style=flat-square">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/><img src="https://img.shields.io/badge/8.0.32-515151?style=flat-square">
<img src="https://img.shields.io/badge/Redis-FF4438?style=flat-square&logo=Redis&logoColor=white"/><img src="https://img.shields.io/badge/7.2.5-515151?style=flat-square">

<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=Swagger&logoColor=black"/>

## 🚃 개발 기술
### Spring Boot, Spring Data JPA

### Redis

### MySQL

### Swagger

### Amazon S3, Amazon EC2


## 🌱 프로젝트 구조
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── hana
│   │           ├── MeteorBackendApplication.java
│   │           ├── PasswordEncoder.java
│   │           ├── app
│   │           │   ├── data
│   │           │   │   └── entity
│   │           │   │       ├── BaseEntity.java
│   │           │   │       ├── Consult.java
│   │           │   │       ├── Contract.java
│   │           │   │       ├── IntegratedPb.java
│   │           │   │       ├── IntegratedVip.java
│   │           │   │       ├── Pb.java
│   │           │   │       ├── RiskType.java
│   │           │   │       ├── Role.java
│   │           │   │       ├── Users.java
│   │           │   │       ├── VIP.java
│   │           │   │       ├── fund
│   │           │   │       │   ├── Fund.java
│   │           │   │       │   ├── FundContract.java
│   │           │   │       │   └── FundSecurity.java
│   │           │   │       ├── portfolio
│   │           │   │       │   ├── Portfolio.java
│   │           │   │       │   └── PortfolioItem.java
│   │           │   │       ├── security
│   │           │   │       │   ├── Security.java
│   │           │   │       │   └── SecurityPrice.java
│   │           │   │       └── suggestion
│   │           │   │           ├── Suggestion.java
│   │           │   │           └── SuggestionItem.java
│   │           │   ├── frame
│   │           │   │   └── BaseService.java
│   │           │   ├── repository
│   │           │   │   ├── ConsultRepository.java
│   │           │   │   ├── ContractRepository.java
│   │           │   │   ├── fund
│   │           │   │   │   ├── FundContractRepository.java
│   │           │   │   │   ├── FundRepository.java
│   │           │   │   │   └── FundSecurityRepository.java
│   │           │   │   ├── portfolio
│   │           │   │   │   ├── PortfolioItemRepository.java
│   │           │   │   │   └── PortfolioRepository.java
│   │           │   │   ├── security
│   │           │   │   │   ├── SecurityPriceRepository.java
│   │           │   │   │   └── SecurityRepository.java
│   │           │   │   ├── suggestion
│   │           │   │   │   ├── SuggestionItemRepository.java
│   │           │   │   │   └── SuggestionRepository.java
│   │           │   │   └── user
│   │           │   │       ├── IntegratedPbRepository.java
│   │           │   │       ├── IntegratedVipRepository.java
│   │           │   │       ├── PbRepository.java
│   │           │   │       ├── UsersRepository.java
│   │           │   │       └── VipRepository.java
│   │           │   └── service
│   │           │       ├── ConsultService.java
│   │           │       ├── ContractService.java
│   │           │       ├── CustomUserDetailsService.java
│   │           │       ├── MainService.java
│   │           │       ├── SuggestionService.java
│   │           │       ├── fund
│   │           │       │   ├── FundContractService.java
│   │           │       │   ├── FundSecurityService.java
│   │           │       │   └── FundService.java
│   │           │       ├── portfolio
│   │           │       │   ├── PortfolioItemService.java
│   │           │       │   └── PortfolioService.java
│   │           │       ├── security
│   │           │       │   ├── SecurityPriceService.java
│   │           │       │   └── SecurityService.java
│   │           │       └── user
│   │           │           ├── PbService.java
│   │           │           ├── UsersService.java
│   │           │           └── VipService.java
│   │           ├── config
│   │           │   ├── HttpConfig.java
│   │           │   ├── JasyptConfig.java
│   │           │   ├── SecurityConfig.java
│   │           │   ├── SwaggerConfig.java
│   │           │   └── WebMvcConfig.java
│   │           ├── controller
│   │           │   ├── ConsultController.java
│   │           │   ├── ContractController.java
│   │           │   ├── FundController.java
│   │           │   ├── IdentityCheckController.java
│   │           │   ├── MainController.java
│   │           │   ├── PbController.java
│   │           │   ├── PortfolioController.java
│   │           │   ├── SecurityController.java
│   │           │   ├── SuggestionController.java
│   │           │   └── VipController.java
│   │           ├── dto
│   │           │   ├── request
│   │           │   │   ├── AddFundToSuggestionRequestDto.java
│   │           │   │   ├── ConsultRegisterDto.java
│   │           │   │   ├── ContractRequestDto.java
│   │           │   │   ├── FinalContractRequestDto.java
│   │           │   │   ├── PbPwdCheckDto.java
│   │           │   │   ├── SuggestionApplyRequestDto.java
│   │           │   │   └── SuggestionApplyRequestItemDto.java
│   │           │   └── response
│   │           │       ├── CodeQuantityDto.java
│   │           │       ├── ConsultResponseDto.java
│   │           │       ├── PurchaseCompositionDto.java
│   │           │       ├── consult
│   │           │       │   ├── ConsultAdminDto.java
│   │           │       │   ├── ConsultAdminItemDto.java
│   │           │       │   ├── ConsultSearchDto.java
│   │           │       │   └── ConsultWebRTCRoomDto.java
│   │           │       ├── fund
│   │           │       │   ├── FundContractDto.java
│   │           │       │   └── FundContractsResponseDto.java
│   │           │       ├── portfolio
│   │           │       │   ├── PortfolioDto.java
│   │           │       │   ├── PortfolioGraphDto.java
│   │           │       │   ├── PortfolioItemDto.java
│   │           │       │   ├── PortfolioItemResponseDto.java
│   │           │       │   └── PortfolioResponseDto.java
│   │           │       ├── security
│   │           │       │   ├── SecurityDto.java
│   │           │       │   └── SecurityItemDto.java
│   │           │       ├── suggestion
│   │           │       │   ├── SuggestionDto.java
│   │           │       │   ├── SuggestionItemCompositionDto.java
│   │           │       │   ├── SuggestionItemDto.java
│   │           │       │   ├── SuggestionItemObtainDto.java
│   │           │       │   └── SuggestionObtainDto.java
│   │           │       └── user
│   │           │           ├── PbDto.java
│   │           │           ├── UsersDto.java
│   │           │           └── VipDto.java
│   │           ├── exception
│   │           │   ├── BadRequestException.java
│   │           │   ├── InternalServerException.java
│   │           │   ├── MeteorControllerAdvice.java
│   │           │   ├── MeteorException.java
│   │           │   ├── NotFoundException.java
│   │           │   └── UnauthorizedException.java
│   │           ├── external
│   │           │   └── aws
│   │           │       ├── S3Config.java
│   │           │       └── S3Service.java
│   │           ├── response
│   │           │   ├── ErrorType.java
│   │           │   └── MeteorResponse.java
│   │           ├── security
│   │           │   └── jwt
│   │           │       ├── JwtAuthenticationEntryPoint.java
│   │           │       ├── JwtAuthenticationFilter.java
│   │           │       └── JwtTokenProvider.java
│   │           └── util
│   │               └── OCRUtil.java
│   └── resources
│       ├── application-dev.yml
│       └── application.yml
└── test
    └── java
        └── com
            └── hana
                ├── MeteorBackendApplicationTests.java
                ├── OCRTests.java
                ├── fund
                │   ├── SelectFundSecuritiesTests.java
                │   └── SelectFundTests.java
                └── portfolio
                    └── SelectPortfolioItemTests.java
```

## 🌼 역할 분담
### 🚲 곽준영
- UI
- 기능
### 🎡 김가원
- UI
- 기능
### ⚓ 김주혜
- UI
- 기능
### 🚔 김하영
- UI
- 기능
### ✈️ 신지연
- UI
- 기능
### ⛪ 이상민
- UI
- 기능

## 🎃 페이지 별 기능

## 🐸 개선 및 추후 발전 계획
