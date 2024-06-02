package com.hana.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class PbDto {
    private final List<VipInfo> vip;

    public PbDto(List<VipInfo> vip) {
        this.vip = vip;
    }

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
        private String status;
        private String name;
        private String riskType;
        private String consultDate;

        @Override
        public String toString() {
            return "VipInfo{" +
                    "vipId=" + vipId +
                    ", status='" + status + '\'' +
                    ", name='" + name + '\'' +
                    ", riskType='" + riskType + '\'' +
                    ", consultDate='" + consultDate + '\'' +
                    '}';
        }
    }
}
