package evan.chen.tutorial.tdd.mvpunittestsample

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProductActivityTest {

    private lateinit var activity: ProductActivity

    @Mock
    private lateinit var presenter: ProductContract.IProductPresenter

    @Before
    fun setupActivity() {

        MockitoAnnotations.initMocks(this)

        val activityController = Robolectric.buildActivity(ProductActivity::class.java)
        activity = activityController.get()

    }

}
