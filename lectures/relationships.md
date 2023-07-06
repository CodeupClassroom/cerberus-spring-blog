show Dog
insert a Dog via Test
fetch all Dogs via Test

create PetOwner
insert an owner thru adminer and associate to dog
create OneToMany and ManyToOne

Dog
@ManyToOne
@JoinColumn (name = "owner_id")
private DogOwner owner;

DogOwner
@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
private List<Dog> dogs;

Show save with a FK inside the object
add a new dog with bob as the owner

explain CascadeType
fetch all dogs via Test
should explode
EXPLAIN
IN THIS CASE
@ToString.exclude dogs in DogOwner
or write our own toString() that leaves dogs out of DogOwner output

M:M
create Toy

create ManyToMany relationships

Dog
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(
name="dogs_toys",
joinColumns={@JoinColumn(name="dog_id")},
inverseJoinColumns={@JoinColumn(name="toy_id")}
)
private List<Toy> toys;


Toy
@ManyToMany(mappedBy = "toys")
private List<Dog> dogs;

WHY NOT TO USE CascadeType.ALL
show a fetch: yay!
delete Fluffy. boo!
switch to CascadeType.PERSIST


