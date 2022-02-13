package com.example.netflix.data.movie

import com.example.netflix.R
import com.example.netflix.model.*

val meta1 = Metadata(
    director = Director(
        name = "Christopher Nolan",
        url = "https://en.wikipedia.org/wiki/Christopher_Nolan"
    ),
    runtime = "1h 32m",
    releaseDate = "July 18, 2008",
    language = "English"

)

val movie1 = Movie(
    id = "movie1",
    title = "THE DARK KNIGHT",
    url = "https://en.wikipedia.org/wiki/The_Dark_Knight_(film)",
    genre = Genre.Action,
    overview = "Christian Bale and director Christopher Nolan reunite following their blockbuster success with Batman Begins! This time, Heath Ledger joins the cast as The Joker, and Aaron Eckhart stars as Harvey Dent in an all-new adventure of The Dark Knight. With the help of Lieutenant Jim Gordon (Gary Oldman) and new district attorney Harvey Dent (Eckhart--Thank You for Smoking), Batman sets out to destroy organized crime in Gotham City forever. The three enjoy early success, but they soon find themselves prey to a rising criminal mastermind known as The Joker (Ledger--Brokeback Mountain), who throws Gotham into anarchy and forces Batman ever closer to crossing the line between hero and vigilante.",
    metadata = meta1,
    imageId = R.drawable.the_dark_knight
)

val meta2 = Metadata(
    director = Director(
        name = "Martin Scorsese",
        url = "https://en.wikipedia.org/wiki/Martin_Scorsese"
    ),
    runtime = "1h 31m",
    releaseDate = "September 26, 2006",
    language = "English"
)

val movie2 = Movie(
    id = "movie2",
    title = "THE DEPARTED",
    url = "https://en.wikipedia.org/wiki/The_Departed",
    genre = Genre.Drama,
    overview = "Martin Scorsese directs an all-star cast in this action-packed thriller set in Boston, where a long-simmering hostility between the police department and an Irish-American gang led by Costello (Academy Award winner Jack Nicholson--About Schmidt, Something's Gotta Give) is primed to explode. The fuse is lit when a gangster (Matt Damon--The Bourne Identity, The Bourne Supremacy) is chosen to infiltrate the police force--and a young cop (Leonardo DiCaprio--The Aviator, Titanic) goes undercover within the gang. Now, when the two moles uncover each other's identity, the battle begins.",
    metadata = meta2,
    imageId = R.drawable.the_departed
)


val meta3 = Metadata(
    director = Director(
        name = "Spike Lee",
        url = "https://en.wikipedia.org/wiki/Spike_Lee"
    ),
    runtime = "3h 21m",
    releaseDate = "November 18, 1992",
    language = "English"
)

val movie3 = Movie(
    id = "movie3",
    title = "MALCOLM X",
    url = "https://en.wikipedia.org/wiki/The_Departed",
    genre = Genre.Drama,
    overview = "Often misunderstood, Malcolm X was one of the leading forces of the United States' Civil Rights Movement. He inspired many--and frightened many--but is destined to be remembered as one of the greatest men of his era. This riveting biography directed by Spike Lee and starring Denzel Washington in an Academy Award-nominated performance reveals the man at the center of a storm of change.",
    metadata = meta3,
    imageId = R.drawable.malcolm_x
)

val meta4 = Metadata(
    director = Director(
        name = "Clint Eastwood",
        url = "https://en.wikipedia.org/wiki/Clint_Eastwood"
    ),
    runtime = "1h 41m",
    releaseDate = "September 17, 2021",
    language = "English, Spanish"
)

val movie4 = Movie(
    id = "movie4",
    title = "MALCOLM X",
    url = "https://en.wikipedia.org/wiki/The_Departed",
    genre = Genre.Drama,
    overview = "Based on the book, “Cry Macho” stars Clint Eastwood as a one‐time rodeo star and washed up horse breeder who, in 1978, takes a job from an ex‐boss to bring the man’s young son home and away from his alcoholic mom. Crossing rural Mexico on their back way to Texas, the unlikely pair faces an unexpectedly challenging journey, during which the world‐weary horseman may find his own sense of redemption through teaching the boy what it means to be a good man.",
    metadata = meta4,
    imageId = R.drawable.cry_macho
)

val meta5 = meta1.copy(
    releaseDate = "September 25, 2021"
)
val movie5 = movie1.copy(
    id = "movie5",
    title = "WRATH OF MAN",
    imageId = R.drawable.wrath_of_man,
    genre = Genre.Thriller,
    metadata = meta5
)
val meta6 = meta4.copy(
    releaseDate = "September 25, 2021"
)
val movie6 = movie4.copy(
    id = "movie6",
    title = "WRATH OF MAN",
    imageId = R.drawable.wrath_of_man,
    genre = Genre.Thriller,
    metadata = meta6
)

val meta7 = meta2.copy(
    releaseDate = "April 23, 2021"
)
val movie7 = movie2.copy(
    id = "movie7",
    title = "MORTAL KOMBAT (2021)",
    imageId = R.drawable.mortal_kombat,
    genre = Genre.Action,
    metadata = meta7
)

val meta8 = meta3.copy(
    releaseDate = "April 12, 2013"
)
val movie8 = movie3.copy(
    id = "movie8",
    title = "BLADE",
    imageId = R.drawable.film42,
    genre = Genre.Drama,
    metadata = meta8
)

val movies = listOf(
    movie1,
    movie2,
    movie3,
    movie4,
    movie5,
    movie6,
    movie7,
    movie8,
)

