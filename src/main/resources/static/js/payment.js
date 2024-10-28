document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    const cardNumber = document.querySelector('[name="cardNumber"]');
    const cardExpiry = document.querySelector('[name="cardExpiry"]');

    cardNumber.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        let formattedValue = '';
        for (let i = 0; i < value.length; i++) {
            if (i > 0 && i % 4 === 0) {
                formattedValue += ' ';
            }
            formattedValue += value[i];
        }
        e.target.value = formattedValue;
    });

    cardExpiry.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        if (value.length > 2) {
            value = value.substr(0, 2) + '/' + value.substr(2, 2);
        }
        e.target.value = value;
    });

    form.addEventListener('submit', function(e) {
        if (!validateCard()) {
            e.preventDefault();
            alert('Please check your card details and try again.');
        }
    });

    function validateCard() {
        const number = cardNumber.value.replace(/\s+/g, '');
        return number.length === 16 && /^\d+$/.test(number);
    }
});