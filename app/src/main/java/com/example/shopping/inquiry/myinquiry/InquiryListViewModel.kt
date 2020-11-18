package com.example.shopping.inquiry.myinquiry

import android.app.Application
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.shopping.api.response.InquiryResponse
import com.example.shopping.common.Prefs
import com.example.shopping.inquiry.InquiryDataSource
import com.example.shopping.inquiry.InquiryPagedAdapter
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class InquiryListViewModel(
        app: Application
) : BaseViewModel(app),
        InquiryPagedAdapter.InquiryItemClickListener {

    var page: InquiryPage? = null
    var requestUserId: Long? = null
    var productOwnerId: Long? = null

    val inquiries by lazy {
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .build()

        val factory =
                object : DataSource.Factory<Long, InquiryResponse>() {
                    override fun create()
                            : DataSource<Long, InquiryResponse> {
                        when (page) {
                            InquiryPage.MY_INQUIRY ->
                                requestUserId = Prefs.userId
                            InquiryPage.PRODUCT_INQUIRY ->
                                productOwnerId = Prefs.userId
                        }
                        return InquiryDataSource(
                                requestUserId = requestUserId,
                                productOwnerId = productOwnerId
                        )
                    }
                }

        LivePagedListBuilder(factory, config).build()
    }

    override fun onClickInquiry(inquiryResponse: InquiryResponse?) {
        inquiryResponse?.run {
            startActivity<ProductDetailActivity> {
                putExtra(
                        ProductDetailActivity.PRODUCT_ID,
                        productId
                )
            }
        }
    }

    override fun onClickAnswer(inquiryResponse: InquiryResponse?) {
        inquiryResponse?.run {
            startActivity<InquiryRegistrationActivity> {
                putExtra(InquiryRegistrationActivity.PRODUCT_ID, productId)
                putExtra(InquiryRegistrationActivity.INQUIRY_ID, id)
                putExtra(
                        InquiryRegistrationActivity.INQUIRY_TYPE,
                        InquiryRegistrationActivity.TYPE_ANSWER
                )
            }
        }
    }

}