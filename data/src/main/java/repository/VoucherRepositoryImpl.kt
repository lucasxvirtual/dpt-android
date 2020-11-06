package repository

import api.VoucherApi
import com.drogariadopovo.domain.model.Voucher
import com.drogariadopovo.domain.repository.VoucherRepository
import io.reactivex.Single
import mapper.VoucherMapper
import javax.inject.Inject

class VoucherRepositoryImpl @Inject constructor(
        private val voucherApi: VoucherApi,
        private val voucherMapper: VoucherMapper) : VoucherRepository {

    override fun getVouchers(): Single<List<Voucher>> {
        return voucherApi.getVouchers().map { voucherMapper.map(it) }
    }
}