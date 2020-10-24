package com.github.tgio.assignment.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.tgio.assignment.misc.IThreadChecker
import com.github.tgio.assignment.misc.LooperChecker
import com.github.tgio.assignment.network.ApiService
import com.github.tgio.assignment.network.components.IErrorConverter
import com.github.tgio.assignment.network.models.APIError
import com.github.tgio.assignment.network.models.BaseResponse
import com.github.tgio.assignment.network.models.Profile
import com.github.tgio.assignment.network.repository.ProfileRepository
import com.github.tgio.assignment.ui.StatefulData
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

class ProfileDetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        LooperChecker.overrideThreadChecker(object : IThreadChecker {
            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    private val profile = Profile(
        id = "007",
        firstName = "George",
        lastName = "Tabatadze",
        age = 29,
        gender = "Male",
        country = "Georgia"
    )

    private val apiService = object: ApiService {
        override suspend fun getList(): Response<BaseResponse<List<String>>> {
            return Response.success(200, BaseResponse(BaseResponse.Status.success, emptyList(), null))
        }

        override suspend fun getProfile(id: String): Response<BaseResponse<Profile>> {
            return when (id) {
                "007" -> Response.success(200, BaseResponse(BaseResponse.Status.success, profile, null))
                else -> Response.error(404, "Profile not found".toResponseBody())
            }
        }
    }

    private val errorConverter = object : IErrorConverter {
        override fun convert(errorBody: ResponseBody?): APIError {
            return APIError(message = errorBody?.string() ?: "Unknown")
        }
    }

    private val repository = ProfileRepository(
        apiService,
        errorConverter,
        Dispatchers.Unconfined
    )

    @Test
    fun testSuccessGetProfile() {
        val vm = ProfileDetailsViewModel(repository)
        Assert.assertTrue(vm.state.value == null)
        vm.getProfileDetails("007")
        Assert.assertTrue(vm.state.value is StatefulData.Success)
    }

    @Test
    fun testErrorGetProfile() {
        val vm = ProfileDetailsViewModel(repository)
        Assert.assertTrue(vm.state.value == null)
        vm.getProfileDetails("-")
        Assert.assertTrue(vm.state.value is StatefulData.Error)
        Assert.assertEquals(APIError("Profile not found"), (vm.state.value as StatefulData.Error).throwable)
    }
}