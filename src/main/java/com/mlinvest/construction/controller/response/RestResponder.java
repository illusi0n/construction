package com.mlinvest.construction.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponder {
    public static ResponseEntity<ApiErrorDto> createBidderNotFoundResponse(Long bidderId) {
        return createGenericNotFoundResponse("bidder", "bidderId", bidderId, ErrorCodes.BIDDER_NOT_FOUND);
    }

    public static ResponseEntity<ApiErrorDto> createTenderNotFoundResponse(Long tenderId) {
        return createGenericNotFoundResponse("tender", "tenderId", tenderId, ErrorCodes.TENDER_NOT_FOUND);
    }

    public static ResponseEntity<ApiErrorDto> createOfferNotFoundResponse(Long offerId) {
        return createGenericNotFoundResponse("offer", "offerId", offerId, ErrorCodes.OFFER_NOT_FOUND);
    }

    public static ResponseEntity<ApiErrorDto> createIssuerActionNotFoundResponse(Long issuerActionId) {
        return createGenericNotFoundResponse("issuer action", "issuerActionId", issuerActionId,
                ErrorCodes.ISSUER_ACTION_NOT_FOUND);
    }

    public static ResponseEntity<ApiErrorDto> createIssuerNotFoundResponse(Long issuerId) {
        return createGenericNotFoundResponse("issuer", "issuerId", issuerId, ErrorCodes.ISSUER_NOT_FOUND);
    }

    public static ResponseEntity<ApiErrorDto> createTenderResultNotFoundResponse(Long tenderResultId) {
        return createGenericNotFoundResponse("tender result", "tenderResultId",
                tenderResultId,ErrorCodes.TENDER_RESULT_NOT_FOUND);
    }

    private static ResponseEntity<ApiErrorDto> createGenericNotFoundResponse(String entityName, String entityIdName, Long entityId,
                                                                             String errorCode) {
        String message = String.format("%s not found %s=%s", entityName, entityIdName, entityId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorDto(message, errorCode));
    }

    public static ResponseEntity<ApiErrorDto> createInternalErrorResponse() {
        String message = "Ooops something went wrong.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorDto(message, ErrorCodes.INTERNAL_SERVER_ERROR));
    }
}
