package api

import io.reactivex.Single
import response.VoucherResponse
import javax.inject.Inject

class VoucherApi @Inject constructor(private val voucherEndPoint: VoucherEndPoint) {

    fun getVouchers() : Single<List<VoucherResponse>>{
        return voucherEndPoint.getVouchers()
    }
}