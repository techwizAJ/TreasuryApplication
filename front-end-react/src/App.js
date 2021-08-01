import React, { useState, useEffect } from "react";
import "./App.css";
import { Card, CardContent } from "@material-ui/core";
import InfoBox from "./InfoBox";
import LineGraph from "./LineGraph";
import Table from "./Table";
import { prettyPrintStat } from "./util";
import numeral from "numeral";
import Map from "./Map";
import "leaflet/dist/leaflet.css";

const getTotalValue = (data) => {
  var total = 0;
  for (let key in data) {
    total = total + data[key];
  }
  return total;
};

const App = () => {
  const [revenueInfo, setRevenueInfo] = useState({});
  const [customerInfo, setCustomerInfo] = useState({});
  const [vendorInfo, setVendorInfo] = useState({});
  const [allInfo, setAllInfo] = useState([]);
  const [casesType, setCasesType] = useState("revenue");
  const [mapCenter, setMapCenter] = useState({ lat: 34.80746, lng: -40.4796 });
  const [mapZoom, setMapZoom] = useState(3);
  const [region, setRegion] = useState({
    na: [47.1164, -101.2996],
    sa: [1.7832, -55.4915],
    europe: [54.526, 15.2551],
    africa: [8.7832, 34.5085],
    apac: [40.9851, 80.5344],
  });

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/invoice/totalRevenue/region")
      .then((response) => response.json())
      .then((data) => {
        setRevenueInfo(data);
      });
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/customers/countOfCustomerBasedOnRegion")
      .then((response) => response.json())
      .then((data) => {
        setCustomerInfo(data);
      });
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/vendors/countOfVendorBasedOnRegion")
      .then((response) => response.json())
      .then((data) => {
        setVendorInfo(data);
      });
  }, []);

  useEffect(() => {
    setAllInfo([region, revenueInfo, customerInfo, vendorInfo]);
  }, [region, revenueInfo, customerInfo, vendorInfo]);

  console.log(revenueInfo);
  console.log(customerInfo);
  console.log(vendorInfo);
  console.log(allInfo);

  return (
    <div className="app">
      <div className="app__left">
        <div className="app__header">
          <h1>Treasury Application</h1>
        </div>
        <div className="app__stats">
          <InfoBox
            onClick={(e) => setCasesType("revenue")}
            title="Revenue"
            active={casesType === "revenue"}
            cases={prettyPrintStat(getTotalValue(revenueInfo))}
            total={numeral(getTotalValue(revenueInfo)).format("0.0a")}
          />
          <InfoBox
            onClick={(e) => setCasesType("customers")}
            title="Customers"
            active={casesType === "customers"}
            cases={prettyPrintStat(getTotalValue(customerInfo))}
            total={numeral(getTotalValue(customerInfo)).format("0.0a")}
          />
          <InfoBox
            onClick={(e) => setCasesType("vendors")}
            title="Vendors"
            active={casesType === "vendors"}
            cases={prettyPrintStat(getTotalValue(vendorInfo))}
            total={numeral(getTotalValue(vendorInfo)).format("0.0a")}
          />
        </div>
        <Map
          countries={allInfo}
          casesType={casesType}
          center={mapCenter}
          zoom={mapZoom}
        />
      </div>
      <Card className="app__right">
        <CardContent>
          <div className="app__information">
            <h3>{casesType} by Regions</h3>
            <Table
              revenueInfo={
                casesType === "revenue"
                  ? revenueInfo
                  : casesType === "customers"
                  ? customerInfo
                  : vendorInfo
              }
            />
            <h3> Revenue Chart </h3>
            <LineGraph />
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default App;
