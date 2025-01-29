import React from "react";
import Card from "../components/Card";
import CardProps from "../model/CardProps";

const CardPage = () => {
  const cardMock: CardProps = {
    id: 1,
    uuid: "550e8400-e29b-41d4-a716-446655440000",
    cardNumber: "1234-5678-9012-3456"
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <Card card={cardMock} id={0} uuid={""} cardNumber={""} />
    </div>
  );
}

export default CardPage;

