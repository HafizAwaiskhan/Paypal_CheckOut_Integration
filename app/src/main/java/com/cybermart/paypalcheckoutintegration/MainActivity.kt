package com.cybermart.paypalcheckoutintegration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton
import com.paypal.checkout.paymentbutton.PaymentButtonContainer
import com.paypal.checkout.paymentbutton.PaymentButtonEligibilityStatus

class MainActivity : AppCompatActivity() {
    lateinit var paymentButtonContainer: PaymentButtonContainer
//    lateinit var payPalButton: PayPalButton
    val TAG = "paypal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        paymentButtonContainer.viewState = PaymentButtonContainerViewState.invoke(
//            onLoading = { ->
//                Log.d("***************", TAG.toString())
//            },
//            onFinish = { fundingEligibilityState, exception ->
//                fundingEligibilityState?.let {
//                    Log.d("**************", fundingEligibilityState.toString())
//                }
//                exception?.let {
//                    Log.d("***************", exception.message.toString())
//                }
//            }
//        )


//        paymentButtonContainer = findViewById(R.id.payment_button_container)
        paymentButtonContainer = findViewById(R.id.payment_button_container)
//        paymentButtonContainer.onEligibilityStatusChanged = { buttonEligibilityStatus: PaymentButtonEligibilityStatus ->
//            Log.v("*************", "OnEligibilityStatusChanged")
//            Log.d("*************", "Button eligibility status: $buttonEligibilityStatus")
//        }
        paymentButtonContainer.setup(
            createOrder =
            CreateOrder { createOrderActions ->
                val order =
                    Order(
                        intent = OrderIntent.CAPTURE,
                        appContext = AppContext(userAction = UserAction.PAY_NOW),
                        purchaseUnitList =
                        listOf(
                            PurchaseUnit(
                                amount =
                                Amount(currencyCode = CurrencyCode.USD, value = "10.00")
                            )
                        )
                    )
                createOrderActions.create(order)
            },
            onApprove =
            OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
                }
            },
            onCancel = OnCancel {
                Log.d("OnCancel", "Buyer canceled the PayPal experience.")
            },
            onError = OnError { errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
            }
        )
    }
}