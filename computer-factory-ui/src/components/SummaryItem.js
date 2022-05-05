import React from "react";

export function SummaryItem({partId, name, quantity, onRemovePart}) {
    return (
        <div className="row">
            <p>{name} <span className="badge bg-dark text-">{quantity}개</span> <button className="btn-danger" onClick={() => onRemovePart(partId)}>X</button></p>
        </div>
    )
}