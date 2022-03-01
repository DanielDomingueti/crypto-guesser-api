package com.domingueti.crypto.responsedto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalGainsDTO {

	private DataGainsResponseDTO dataGainsResponseDTO;
	
	private String investedAmount;
	private String _1_hour_ago;
	private String _24_hours_ago;
	private String _1_week_ago;
	private String _1_month_ago;
	private String _3_months_ago;
	private String _1_year_ago;
	
}
