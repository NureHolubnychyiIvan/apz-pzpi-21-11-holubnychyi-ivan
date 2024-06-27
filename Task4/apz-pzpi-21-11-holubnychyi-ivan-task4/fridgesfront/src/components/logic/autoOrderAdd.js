
export async function addOrder(productList) {
  try {
    const orders = productList.map((product) => {
      return {
        dateDelivery: new Date(),
        number: product.count,
        access: 1,
        product: {
          name: product.name,
          weight: product.weight,
        },
      };
    });
    
    await sendOrdersToServer(orders);
  } catch (error) {
    console.error('Error placing orders:', error.message);
  }
}
    
async function sendOrdersToServer(orders) {
  try {
    const response = await fetch('http://localhost:8080/user/autoOrdering/addOrder', {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(orders),
    });
    
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    } else {
      alert("Успішно додано!");
    }
    
  } catch (error) {
    console.error('Error sending orders to server:', error.message);
  }
}