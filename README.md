# ProtocolX - 암호화폐 거래소 플랫폼

ProtocolX는 암호화폐 거래소 플랫폼을 개발하는 프로젝트입니다.

## 프로젝트 구조

```
protocol-x/
├── common/                     # 공통 라이브러리 및 유틸리티
├── user-auth-service/          # 사용자 인증 서비스
├── api-gateway/                # API 게이트웨이
├── order-management-service/   # 주문 관리 서비스
├── matching-engine/            # 매칭 엔진
```

### 설치 및 실행

```
docker-compose up -d
```

#### 실핼 중 재실행

```
docker-compose down -v
docker-compose up -d
```
