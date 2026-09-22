document.addEventListener('DOMContentLoaded', function() {
    const loadingAnimation = document.getElementById('loadingAnimation');
    const mainContent = document.getElementById('mainContent');
    const dreamButton = document.getElementById('dreamButton');
    
    // Simulate loading time
    setTimeout(() => {
        // Hide loading animation
        loadingAnimation.classList.add('hidden');
        
        // Show main content
        mainContent.classList.remove('hidden');
        setTimeout(() => {
            mainContent.classList.add('show');
        }, 100);

        // Show and animate button
        dreamButton.classList.remove('hidden');
        dreamButton.classList.add('show');
    }, 3000);

    dreamButton.addEventListener('click', function() {
        // Open Dreams Diary
        window.open('dreams-diary.html', '_blank');
        // Open Dreams Total
        window.open('dreams-total.html', '_blank');
    });
}); 