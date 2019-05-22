package user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import user.domain.ServiceProductPrice

/**
 * Create by lucy on 2019-01-29
 **/
@Repository
interface ProductPriceRepository  : JpaRepository<ServiceProductPrice, Long> {
}