package com.domingueti.crypto.services;

import org.springframework.stereotype.Service;

import com.domingueti.crypto.responsedto.DataGainsResponseDTO;
import com.domingueti.crypto.responsedto.DataResponseDTO;
import com.domingueti.crypto.responsedto.TotalGainsDTO;

@Service
public class CalculateGainsService {

	public TotalGainsDTO execute(String amountString, DataResponseDTO dataResponseDTO) {
		
		Double amount = Double.parseDouble(amountString);
		Double price_usd = Double.parseDouble(dataResponseDTO.getData().getMarket_data().getPrice_usd());
		Double percent_change_usd_last_1_hour = Double.parseDouble(dataResponseDTO.getData().getMarket_data().getPercent_change_usd_last_1_hour());
		Double percent_change_usd_last_24_hours = Double.parseDouble(dataResponseDTO.getData().getMarket_data().getPercent_change_usd_last_24_hours());
		Double percent_change_last_1_week = Double.parseDouble(dataResponseDTO.getData().getRoi_data().getPercent_change_last_1_week());
		Double percent_change_last_1_month = Double.parseDouble(dataResponseDTO.getData().getRoi_data().getPercent_change_last_1_month());
		Double percent_change_last_3_months = Double.parseDouble(dataResponseDTO.getData().getRoi_data().getPercent_change_last_3_months());
		Double percent_change_last_1_year = Double.parseDouble(dataResponseDTO.getData().getRoi_data().getPercent_change_last_1_year());

		String last_1_hour = String.valueOf(amount + (amount * percent_change_usd_last_1_hour / 100));
		String last_24_hours = String.valueOf(amount + (amount * percent_change_usd_last_24_hours / 100));
		String last_1_week = String.valueOf(amount + (amount * percent_change_last_1_week / 100));
		String last_1_month = String.valueOf(amount + (amount * percent_change_last_1_month / 100));
		String last_3_months = String.valueOf(amount + (amount * percent_change_last_3_months / 100));
		String last_1_year = String.valueOf(amount + (amount * percent_change_last_1_year / 100));
		
		DataGainsResponseDTO dataGainsResponseDTO = new DataGainsResponseDTO(dataResponseDTO.getData().getId(), dataResponseDTO.getData().getName(), dataResponseDTO.getData().getSymbol());
		
		TotalGainsDTO totalGainsDTO = new TotalGainsDTO(dataGainsResponseDTO, amountString, last_1_hour, last_24_hours, last_1_week, last_1_month, last_3_months, last_1_year);
		
		return totalGainsDTO;
		
	}
}
