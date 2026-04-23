let cart = JSON.parse(localStorage.getItem("cart")) || [];

// update cart count
window.onload = function () {
    let count = document.getElementById("cartCount");
    if (count) {
        count.innerText = "Cart: " + cart.length;
    }
};

// add to cart
function addToCart(name, price) {
    let product = { name, price };

    cart.push(product);

    localStorage.setItem("cart", JSON.stringify(cart));

    document.getElementById("cartCount").innerText = "Cart: " + cart.length;

    alert(name + " added to cart!");
}

// show cart items
function displayCart() {
    let cartItemsDiv = document.getElementById("cartItems");
    let total = 0;

    if (!cartItemsDiv) return;

    cartItemsDiv.innerHTML = "";

    cart.forEach((item, index) => {
        total += item.price;

        cartItemsDiv.innerHTML += `
            <div class="card">
                <h3>${item.name}</h3>
                <p>₹${item.price}</p>
                <button onclick="removeItem(${index})">Remove</button>
            </div>
        `;
    });

    document.getElementById("totalPrice").innerText = "Total: ₹" + total;
}

// remove item
function removeItem(index) {
    cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
    displayCart();
}

// clear cart
function clearCart() {
    localStorage.removeItem("cart");
    cart = [];
    displayCart();
}

// filter category
function filterProducts(category) {
    let products = document.querySelectorAll(".card");

    products.forEach((card) => {
        if (category === "all" || card.dataset.category === category) {
            card.style.display = "block";
        } else {
            card.style.display = "none";
        }
    });
}

// run cart display
displayCart();