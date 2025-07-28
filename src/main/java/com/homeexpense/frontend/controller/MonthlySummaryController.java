package com.homeexpense.frontend.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MonthlySummaryController {

    @FXML
    private TableView<AttendanceSummary> summaryTable;

    @FXML
    private TableColumn<AttendanceSummary, String> monthColumn;

    @FXML
    private TableColumn<AttendanceSummary, Integer> presentColumn;

    @FXML
    private TableColumn<AttendanceSummary, Integer> absentColumn;

    @FXML
    public void initialize() {
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        presentColumn.setCellValueFactory(new PropertyValueFactory<>("present"));
        absentColumn.setCellValueFactory(new PropertyValueFactory<>("absent"));

        ObservableList<AttendanceSummary> data = FXCollections.observableArrayList();
        // Add dummy data for now — will connect to backend later
        data.add(new AttendanceSummary("July", 25, 6));
        data.add(new AttendanceSummary("June", 26, 4));
        summaryTable.setItems(data);
    }

    public static class AttendanceSummary {
        private final String month;
        private final int present;
        private final int absent;

        public AttendanceSummary(String month, int present, int absent) {
            this.month = month;
            this.present = present;
            this.absent = absent;
        }

        public String getMonth() {
            return month;
        }

        public int getPresent() {
            return present;
        }

        public int getAbsent() {
            return absent;
        }
    }
}
