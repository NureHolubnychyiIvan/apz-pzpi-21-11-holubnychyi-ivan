
export async function sendSubscription() {
    try {
        const currentDate = new Date();
        const endDate = new Date();
        endDate.setMonth(endDate.getMonth() + 6);

        const user = await getUser();

        const subscriptionData = {
            beginDate: currentDate,
            endDate: endDate,
            price: 19,
            user: user,
        };

        const response = await fetch("http://localhost:8080/user/addSubscription", {
            method: "POST",
            headers: { 
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("accessToken")}`, 
            },
            body: JSON.stringify(subscriptionData),
        });

        if (response.ok) {
            console.log('Subscription added successfully!');
        } else {
            console.error('Failed to add subscription.');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function getUser() {
    try {
        const response = await fetch(`/user/account/${localStorage.getItem("email")}`, {
            method: "GET",
            headers: {
                Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const user = await response.json();
            return user;
        } else {
            console.error('Failed to get user ID.');
            return null;
        }
    } catch (error) {
        console.error('Error:', error);
        return null;
    }
}
