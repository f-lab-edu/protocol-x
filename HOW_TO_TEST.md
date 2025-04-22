# JPA Repository 테스트 방법 (@ContainerDataJpaTest)

`common-data-jpa` 모듈의 `@ContainerDataJpaTest` 어노테이션을 사용하면 실제 MySQL 데이터베이스 환경을 Testcontainers를 통해 자동으로 구성하고 `@DataJpaTest`를 수행합니다.

## 사용 예시

```kotlin
import com.exchange.protocolx.common.jpa.test.config.ContainerDataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Test

@ContainerDataJpaTest
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
) {
    @Test
    fun `사용자 저장 및 조회 테스트`() {
        // 테스트 코드 작성...
    }
}
```
