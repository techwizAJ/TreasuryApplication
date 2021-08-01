import React from "react";
import numeral from "numeral";
import { Circle, Popup } from "react-leaflet";

const casesTypeColors = {
  revenue: {
    hex: "#CC1034",
    rgb: "rgb(204, 16, 52)",
    half_op: "rgba(204, 16, 52, 0.5)",
    multiplier: 12000,
  },
  customers: {
    hex: "#7dd71d",
    rgb: "rgb(125, 215, 29)",
    half_op: "rgba(125, 215, 29, 0.5)",
    multiplier: 12000,
  },
  vendors: {
    hex: "#fb4443",
    rgb: "rgb(251, 68, 67)",
    half_op: "rgba(251, 68, 67, 0.5)",
    multiplier: 12000,
  },
};

export const prettyPrintStat = (stat) =>
  stat ? `+${numeral(stat).format("0.0a")}` : "+0";

export const showDataOnMap = (data, casesType = "revenue") =>
  data.map((country) => (
    <Circle
      center={[country["apac"], country["apac"]]}
      color={casesTypeColors[casesType].hex}
      fillColor={casesTypeColors[casesType].hex}
      fillOpacity={0.8}
      radius={
        Math.log2(country[casesType]) * casesTypeColors[casesType].multiplier
      }
    >
      <Popup>
        <div className="info-container">
          <div className="info-name">{country.country}</div>
          <div className="info-confirmed">
            Cases: {numeral(country.cases).format("0,0")}
          </div>
          <div className="info-recovered">
            Recovered: {numeral(country.recovered).format("0,0")}
          </div>
          <div className="info-deaths">
            Deaths: {numeral(country.deaths).format("0,0")}
          </div>
        </div>
      </Popup>
    </Circle>
  ));

export const showNewDataOnMap = (data, casesType = "revenue") => {
  console.log(data[0]);
  const mapItem = [];
  const mapRegion = data[0];
  for (let key in mapRegion) {
    mapItem.push(
      <Circle
        center={mapRegion[key]}
        color={casesTypeColors[casesType].hex}
        fillColor={casesTypeColors[casesType].hex}
        fillOpacity={0.8}
        radius={
          Math.log2(getTotalValue(data, casesType, key)) *
          casesTypeColors[casesType].multiplier *
          2.5
        }
      >
        <Popup>
          <div className="info-container">
            <div className="info-name">{key}</div>
            <div className="info-confirmed">
              Total {casesType}{" "}
              {numeral(getTotalValue(data, casesType, key)).format("0,0")}
            </div>
          </div>
        </Popup>
      </Circle>
    );
  }
  return mapItem;
};

const getTotalValue = (data, casesType, region) => {
  if (casesType === "customers") {
    data = data[2];
  } else if (casesType === "vendors") {
    data = data[3];
  } else {
    data = data[1];
  }
  var total = 1;
  total = data[region] != null ? data[region] : 10;
  return total;
};
