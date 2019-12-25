package com.iamvickyav.RateLimitApi.domain.request;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class UserQuotaUpdateRequest {

    @NotNull
    public Map<Integer, List<QuotaUpdateRequest>> request;
}
