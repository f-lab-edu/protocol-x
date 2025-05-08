# ProtocolX - 암호화폐 거래소 플랫폼

ProtocolX는 암호화폐 거래소 플랫폼을 개발하는 프로젝트입니다.

## 프로젝트 구조

```
protocol-x/
├── user-auth-service/          # 사용자 인증 서비스
├── api-gateway/                # API 게이트웨이
├── order-management-service/   # 주문 관리 서비스
├── matching-engine/            # 매칭 엔진
```

### 설치 및 실행

#### 로컬 환경

```bash
docker-compose -f docker-compose.local.yml up -d
```

#### 개발 환경

```bash
docker-compose -f docker-compose.dev.yml up -d
```

### 로컬 환경 실행 중 재실행

```bash
docker-compose -f docker-compose.local.yml down -v && docker-compose -f docker-compose.local.yml up -d --build
```
