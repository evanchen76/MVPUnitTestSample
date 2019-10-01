package evan.chen.tutorial.tdd.mvpunittestsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import evan.chen.tutorial.tdd.mvpunittestsample.api.ProductAPI
import evan.chen.tutorial.tdd.mvpunittestsample.api.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class ProductActivity : AppCompatActivity(), ProductContract.IProductView {

    private val productId = "pixel3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productRepository = ProductRepository(ProductAPI())
        val productPresenter = ProductPresenter(this, productRepository)

        buy.setOnClickListener {
            val numbers = productItems.text.toString().toInt()
            productPresenter.buy(productId, numbers)
        }

        productPresenter.getProduct(productId)
    }

    override fun onGetResult(productResponse: ProductResponse) {
        productName.text = productResponse.name
        productDesc.text = productResponse.desc

        val currencyFormat = NumberFormat.getCurrencyInstance()
        currencyFormat.maximumFractionDigits = 0
        val price = currencyFormat.format(productResponse.price)
        productPrice.text = price
    }

    override fun onBuySuccess() {
        Toast.makeText(this, "購買成功", Toast.LENGTH_LONG).show();
    }

    override fun onBuyFail() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("購買失敗").setTitle("錯誤")
        builder.show()
    }
}
