package com.mlinvest.construction.util;

import com.mlinvest.construction.controller.dto.OfferDto;
import com.mlinvest.construction.persistence.model.Offer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static class OfferMapper {
        public static List<OfferDto> from(List<Offer> offers) {
            if (offers == null || offers.isEmpty()) {
                return Collections.emptyList();
            }

            return offers.stream().map(OfferDto::of).collect(Collectors.toList());
        }
    }
}
