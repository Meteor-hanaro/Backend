package com.hana.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PbDto {
    private final List<VipInfo> vip;
    private final List<VipState> state;

    @Override
    public String toString() {
        return "PbDto{" +
                ", vip=" + vip +
                '}';
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class VipInfo {
        private Long vipId;
        private String name;
        private String email;
        private String riskType;
        private String consultDate;
        private Long pbId;

        @Override
        public String toString() {
            return "VipInfo{" +
                    "vipId=" + vipId +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", riskType='" + riskType + '\'' +
                    ", consultDate='" + consultDate + '\'' +
                    '}';
        }
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class VipState {
        private boolean state;

        @Override
        public String toString() {
            return "VipState{" +
                    "state=" + state +
                    '}';
        }
    }
}
