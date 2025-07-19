import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ispalindrome.data.services.response.User
import com.example.ispalindrome.data.services.response.UserResponse
import com.example.ispalindrome.data.services.retrofit.ApiConfig
import com.example.ispalindrome.data.services.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            val response: UserResponse = apiService.getUsers(page = page)
            val users = response.data
            val totalPages = response.totalPages

            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= totalPages) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        // Try to find the page key closest to anchor position
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }
}
