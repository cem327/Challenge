package org.cem.controller;

import lombok.RequiredArgsConstructor;
import org.cem.utility.DataSample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.cem.constants.RestApiUrls.CREATE_SAMPLE_DATA;
import static org.cem.constants.RestApiUrls.DATA;

@RestController
@RequestMapping(DATA)
@RequiredArgsConstructor
public class DataController {

    private final DataSample dataSample;

    @GetMapping(CREATE_SAMPLE_DATA)
    public void createSampleData() {
        dataSample.init();
    }
}
