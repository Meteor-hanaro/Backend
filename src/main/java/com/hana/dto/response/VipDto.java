package com.hana.dto.response;

import com.hana.app.data.entity.Consult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class VipDto {
    private final VipInfo vipInfo;
    private final PbInfo pbInfo;
    private final List<Consult> consultList;

    public VipDto(VipInfo vipInfo, PbInfo pbInfo, List<Consult> consultList){
        this.vipInfo = vipInfo;
        this.pbInfo = pbInfo;
        this.consultList = consultList;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class VipInfo {
        private final Long vipId;
        private final String password;
        private final String name;
        private final Boolean isvip;
        private final String riskType;
        private final String riskTestDate;
        private final boolean hasConsult;

        @Override
        public String toString() {
            return "VipInfo{" +
                    "vipId=" + vipId +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    ", isvip=" + isvip +
                    ", riskType='" + riskType + '\'' +
                    ", riskTestDate='" + riskTestDate + '\'' +
                    ", hasConsult=" + hasConsult +
                    '}';
        }
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class PbInfo {
        private final Long pbId;
        private final String name;
        private final String email;
        private final String phone;
        private final String image;
        private final String introduce;

        @Override
        public String toString() {
            return "PbInfo{" +
                    "pbId=" + pbId +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", image='" + image + '\'' +
                    ", introduce='" + introduce + '\'' +
                    '}';
        }
    }
}
