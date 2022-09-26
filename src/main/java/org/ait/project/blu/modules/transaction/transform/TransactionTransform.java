package org.ait.project.blu.modules.transaction.transform;

import org.ait.project.blu.modules.transaction.dto.request.TransferOnUsRequest;
import org.ait.project.blu.modules.transaction.dto.response.TransferOnUsResponse;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.request.TransferOnUsRequestDto;
import org.ait.project.blu.shared.openfeign.blu.transfer.onus.response.TransferOnUsResponseDto;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface TransactionTransform {

    TransferOnUsRequestDto copyFromFrontEnd(TransferOnUsRequest transferOnUsRequest);

    TransferOnUsResponse copyResultFromBackEnd(TransferOnUsResponseDto transferOnUsResponse);

}
