import React from "react";
import "./Table.css";
import numeral from "numeral";

const tableInfo = (revenueInfo) => {
  const itemMap = [];
  for (const key in revenueInfo) {
    itemMap.push(
      <tr>
        <td>{key}</td>
        <td>
          <strong>{numeral(revenueInfo[key]).format("0,0")}</strong>
        </td>
      </tr>
    );
  }
  return itemMap;
};

function Table({ revenueInfo }) {
  return <div className="table">{tableInfo(revenueInfo)}</div>;
}

export default Table;
