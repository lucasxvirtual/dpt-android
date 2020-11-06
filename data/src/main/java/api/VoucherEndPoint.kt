package api

import io.reactivex.Single
import response.VoucherResponse
import retrofit2.http.GET

interface VoucherEndPoint {

    @GET("core/voucher/")
    fun getVouchers() : Single<List<VoucherResponse>>
}