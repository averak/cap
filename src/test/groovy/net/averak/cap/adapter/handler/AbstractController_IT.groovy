package net.averak.cap.adapter.handler

import net.averak.cap.AbstractDatabaseSpec
import net.averak.cap.adapter.handler.schema.ErrorResponse
import net.averak.cap.core.exception.AbstractException
import net.averak.cap.infrastructure.i18n.MessageUtils
import net.averak.cap.testutils.JsonUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.core.Authentication
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.util.MultiValueMap
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared

import javax.annotation.Nullable

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity

class AbstractController_IT extends AbstractDatabaseSpec {

    private MockMvc mockMvc

    @Autowired
    private WebApplicationContext webApplicationContext

    @Autowired
    private PlatformTransactionManager transactionManager

    @Shared
    protected MockHttpSession session = new MockHttpSession()

    @Shared
    protected Optional<Authentication> authentication = Optional.empty()

    /**
     * GET request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder getRequest(final String path) {
        return MockMvcRequestBuilders.get(path)
            .session(this.session)
            .with(csrf())
            .with(authentication(this.authentication.orElse(null)))
    }

    /**
     * POST request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path) {
        return MockMvcRequestBuilders.post(path)
            .session(this.session)
            .with(csrf())
            .with(authentication(this.authentication.orElse(null)))
    }

    /**
     * POST request (Form)
     *
     * @param path path
     * @param params query params
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path, final MultiValueMap<String, String> params) {
        return MockMvcRequestBuilders.post(path)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .params(params)
            .session(this.session)
            .with(csrf())
            .with(authentication(this.authentication.orElse(null)))
    }

    /**
     * POST request (JSON)
     *
     * @param path path
     * @param content request body
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path, @Nullable final Object content) {
        return MockMvcRequestBuilders.post(path)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JsonUtils.toJson(content))
            .session(this.session)
            .with(csrf())
            .with(authentication(this.authentication.orElse(null)))
    }

    /**
     * PUT request (JSON)
     *
     * @param path path
     * @param content request body
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder putRequest(final String path, @Nullable final Object content) {
        return MockMvcRequestBuilders.put(path)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JsonUtils.toJson(content))
            .session(this.session)
            .with(csrf())
            .with(authentication(this.authentication.orElse(null)))
    }

    /**
     * DELETE request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder deleteRequest(final String path) {
        return MockMvcRequestBuilders.delete(path)
            .session(this.session)
            .with(csrf())
            .with(authentication(this.authentication.orElse(null)))
    }

    /**
     * Execute request
     *
     * @param request HTTP request builder
     * @param status expected HTTP status
     *
     * @return MVC result
     */
    MvcResult execute(final MockHttpServletRequestBuilder request, final HttpStatus status) {
        final result = mockMvc.perform(request).andReturn()

        assert result.response.status == status.value()
        return result
    }

    /**
     * Execute request / return response
     *
     * @param request HTTP request builder
     * @param status expected HTTP status
     * @param clazz response class
     *
     * @return response
     */
    def <T> T execute(final MockHttpServletRequestBuilder request, final HttpStatus status, final Class<T> clazz) {
        final result = mockMvc.perform(request).andReturn()

        assert result.response.status == status.value()
        return JsonUtils.fromJson(result.getResponse().getContentAsString(), clazz)
    }

    /**
     * Execute request / verify exception
     *
     * @param request HTTP request builder
     * @param exception expected exception
     *
     * @return error response
     */
    ErrorResponse execute(final MockHttpServletRequestBuilder request, final AbstractException exception) {
        final result = mockMvc.perform(request).andReturn()
        final response = JsonUtils.fromJson(result.response.contentAsString, ErrorResponse.class)

        assert result.response.status == exception.httpStatus.value()
        assert response.code == exception.errorCode.name
        assert response.message == MessageUtils.getMessage(exception.errorCode.messageSourceKey)
        return response
    }

    /**
     * setup before test case
     */
    def setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.webApplicationContext)
            .addFilter(({ request, response, chain ->
                response.setCharacterEncoding("UTF-8")
                chain.doFilter(request, response)
            }))
            .apply(springSecurity())
            .build()
    }

    def cleanup() {
        this.authentication = Optional.empty()
    }

}