package com.yansha.simplekotlinproject.ui.restaurant

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yansha.restaurantreview.data.response.CustomerReviewsItem
import com.yansha.restaurantreview.data.response.PostReviewResponse
import com.yansha.restaurantreview.data.response.Restaurant
import com.yansha.restaurantreview.data.response.RestaurantResponse
import com.yansha.restaurantreview.data.retrofit.ApiConfig
import com.yansha.simplekotlinproject.databinding.RestaurantMainBinding
import com.yansha.simplekotlinproject.ui.ReviewAdapter
import retrofit2.Call
import retrofit2.Response

class RestaurantFragment : Fragment() {

    private var _binding: RestaurantMainBinding? = null
    private val binding get() = _binding!!

    private val restaurantId = "uewq1zg2zlskfw1e867"

    companion object {
        private const val TAG = "RestaurantFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RestaurantMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvReview.layoutManager = layoutManager

        val itemDescription = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDescription)

        findRestaurant()

        binding.btnSend.setOnClickListener { view ->
            postReview(binding.edReview.text.toString())
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun findRestaurant() {
        showLoading(true)

        val client = ApiConfig.getApiService().getRestaurant(restaurantId)
        client.enqueue(object : retrofit2.Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setRestaurantData(responseBody.restaurant)
                        setReviewData(responseBody.restaurant.customerReviews)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setReviewData(consumereview: List<CustomerReviewsItem>) {
        val adapter = ReviewAdapter()
        adapter.submitList(consumereview)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setRestaurantData(restaurant: Restaurant) {
        binding.tvtitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description

        Glide.with(this)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.vPicture)
    }

    private fun postReview(review: String) {
        showLoading(false)
        val client = ApiConfig.getApiService().postReview(restaurantId, "Yansha", review)
        client.enqueue(object : retrofit2.Callback<PostReviewResponse> {
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: Response<PostReviewResponse>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    setReviewData(responseBody.customerReviews)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}