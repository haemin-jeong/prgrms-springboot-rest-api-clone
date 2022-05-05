import React from "react";
import {Product} from "./Product";

export function ProductList({parts = [], onAddClick}) {
    return (
        <React.Fragment>
            <h5>부품 목록</h5>
            <ul className="list-group products">
                {parts.map(v =>
                    <li key={v.partId} className="list-group-item d-flex mt-3">
                        <Product {...v} onAddClick={onAddClick}/>
                    </li>
                )}
            </ul>
        </React.Fragment>
    )
}