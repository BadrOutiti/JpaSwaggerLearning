import React from 'react';
import CardProps from "../model/CardProps";


const Card = (card: CardProps) => {
  return (
    <div className="p-4 border rounded-lg shadow-md bg-white">
      <h2 className="text-lg font-semibold">Card Details</h2>
      <p><strong>ID:</strong> {card.id}</p>
      <p><strong>UUID:</strong> {card.uuid}</p>
      <p><strong>Card Number:</strong> {card.cardNumber}</p>
    </div>
  );
};

export default Card;
